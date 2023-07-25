package dev.sweety.quizgame.commands.api;


import dev.sweety.quizgame.QuizGame;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@UtilityClass
public class ReflectionUtil {

    public void register(String pack) {
        ReflectionUtil.getCommand(pack).forEach(Command::registerExecutor);
    }

    @SneakyThrows
    public List<Command> getCommand(String packageName) {
        List<Command> commands = new ArrayList<>();
        for (Class<?> clazz : getClassesInPackage(packageName)) {
            if (clazz.isAnnotationPresent(Sweety.class)) {
                commands.add(((Command) clazz.getConstructor().newInstance()));
            }
        }
        return commands;
    }

    public Class<?>[] getClassesInPackage(String packageName) {
        try {
            Set<String> classnames = getClassNamesFromJarFile(Paths.get(ReflectionUtil.path()).toFile());
            List<Class<?>> classes = new ArrayList<>();

            for (String classname : classnames) {
                try {
                    if (classname.startsWith(packageName)) {
                        Class<?> clazz = Class.forName(classname);
                        classes.add(clazz);
                    }
                } catch (NoClassDefFoundError | ClassNotFoundException |
                         UnsupportedClassVersionError ignored) {
                }
            }

            return classes.toArray(new Class[0]);
        } catch (Exception exception) {

            File directory = getPackageDirectory(packageName);

            if (!directory.exists()) {
                throw new IllegalArgumentException("Could not get directory resource for package " + packageName);
            }

            return getClassesInPackage(packageName, directory);
        }
    }

    public Set<String> getClassNamesFromJarFile(File givenFile) throws IOException {
        Set<String> classNames = new HashSet<>();
        try (JarFile jarFile = new JarFile(givenFile)) {
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName()
                            .replace("/", ".")
                            .replace(".class", "");
                    classNames.add(className);
                }
            }
            return classNames;
        }
    }

    private Class<?>[] getClassesInPackage(String packageName, File directory) {
        List<Class<?>> classes = new ArrayList<>();

        for (String filename : Objects.requireNonNull(directory.list())) {
            if (filename.endsWith(".class")) {
                String classname = buildClassname(packageName, filename);
                try {
                    classes.add(Class.forName(classname));
                } catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
            } else if (!filename.contains(".")) {
                String name = packageName + (packageName.endsWith(".") ? "" : ".") + filename;
                classes.addAll(Arrays.asList(getClassesInPackage(name, getPackageDirectory(name))));
            }
        }

        return classes.toArray(new Class[0]);
    }

    public String buildClassname(String packageName, String filename) {
        return packageName + '.' + filename.replace(".class", "");
    }

    private File getPackageDirectory(String packageName) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }

        URL resource = cld.getResource(packageName.replace('.', '/'));

        if (resource == null) {
            throw new RuntimeException("Package " + packageName + " not found on classpath.");
        }

        return new File(resource.getFile());
    }

    public String path() throws URISyntaxException {
        return new File(QuizGame.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI()).getPath();
    }
}