import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import zapsdtest.simpletest.apsd.classes.containers.sequences.CircularVectorITest;
import zapsdtest.simpletest.apsd.classes.containers.sequences.DynCircularVectorITest;
import zapsdtest.simpletest.apsd.classes.containers.sequences.DynVectorITest;
import zapsdtest.simpletest.apsd.classes.containers.sequences.VectorITest;

public class Main {

  private static final String STANDALONE_JUNIT_REL_PATH =
      "zapsdtest/zjunit/junit-platform-console-standalone-1.12.1.jar";

  static public void main(String[] args) {

    System.out.println("APSD Libraries 2026!");

    runSequenceTests();

  }

  private static void runSequenceTests() {
    // Riferimenti espliciti alle classi di test delle sequenze:
    // garantiscono che javac compili automaticamente solo i test di sequences
    // (senza fallire sui test di deqs/collections/ordered non ancora implementati)
    // ed eseguono i test nell'ordine 01 -> 04.
    Class<?>[] sequenceTests = {
      VectorITest.class,
      CircularVectorITest.class,
      DynVectorITest.class,
      DynCircularVectorITest.class
    };

    String[] junitArgs = new String[5 + sequenceTests.length * 2];
    int idx = 0;
    junitArgs[idx++] = "execute";
    junitArgs[idx++] = "--details";
    junitArgs[idx++] = "tree";
    junitArgs[idx++] = "--details-theme";
    junitArgs[idx++] = "ascii";
    for (Class<?> testClass : sequenceTests) {
      junitArgs[idx++] = "--select-class";
      junitArgs[idx++] = testClass.getName();
    }

    try {
      Path rootDir = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
      if (!Files.isDirectory(rootDir)) {
        rootDir = Paths.get(".").toAbsolutePath().normalize();
      }
      Path standaloneJar = rootDir.resolve(STANDALONE_JUNIT_REL_PATH);
      if (!Files.exists(standaloneJar)) {
        standaloneJar = Paths.get(STANDALONE_JUNIT_REL_PATH).toAbsolutePath().normalize();
      }
      if (!Files.exists(standaloneJar)) {
        throw new IllegalStateException("JAR JUnit standalone non trovato: " + standaloneJar);
      }

      // ClassLoader isolato sul solo JAR standalone per evitare il conflitto di versioni
      // presente in zapsdtest/zjunit/* (jupiter 5.10.3 / 5.13.4 vs platform 1.12.1).
      URL[] urls = {
        rootDir.toUri().toURL(),
        standaloneJar.toUri().toURL()
      };

      ClassLoader prevCl = Thread.currentThread().getContextClassLoader();
      try (URLClassLoader junitCl = new URLClassLoader(urls, ClassLoader.getPlatformClassLoader())) {
        Thread.currentThread().setContextClassLoader(junitCl);
        Class<?> launcherClass = Class.forName("org.junit.platform.console.ConsoleLauncher", true, junitCl);
        Method runMethod = launcherClass.getMethod("run", PrintWriter.class, PrintWriter.class, String[].class);

        PrintWriter out = new PrintWriter(System.out, true);
        PrintWriter err = new PrintWriter(System.err, true);
        runMethod.invoke(null, out, err, junitArgs);
        out.flush();
        err.flush();
      } finally {
        Thread.currentThread().setContextClassLoader(prevCl);
      }
    } catch (Exception e) {
      System.err.println("Errore durante l'esecuzione dei test sulle sequenze:");
      e.printStackTrace(System.err);
    }
  }

}