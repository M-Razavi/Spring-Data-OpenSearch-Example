package org.opensearch.data.example.repository;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.net.HttpURLConnection;
import java.nio.file.Paths;
import java.time.Duration;

public class SingletonContainer {

    private static final GenericContainer<?> openSearchContainer = createOpenSearchContainer();
    private static boolean isStarted = false;

    public static synchronized void startContainer() {
        if (!isStarted) {
            System.out.println("Starting container...");
            openSearchContainer.start();
            isStarted = true;
            System.out.println("Container started.");
        }
    }

    public static GenericContainer<?> getInstance() {
        return openSearchContainer;
    }

    private static GenericContainer<?> createOpenSearchContainer() {
        ImageFromDockerfile image = new ImageFromDockerfile()
                .withDockerfile(Paths.get("develop-scripts/Dockerfile"));
        return new GenericContainer<>(image)
                .withEnv("discovery.type", "single-node")
                .withEnv("DISABLE_SECURITY_PLUGIN", "true")
                .withEnv("OPENSEARCH_JAVA_OPTS", "-Xms512m -Xmx512m")
                .withExposedPorts(9200, 9600)
                .waitingFor(
                        new HttpWaitStrategy()
                                .forPort(9200)
                                .forStatusCodeMatching(response -> response == HttpURLConnection.HTTP_OK ||
                                        response == HttpURLConnection.HTTP_UNAUTHORIZED)
                                .withStartupTimeout(Duration.ofMinutes(2))
                );
    }
}
