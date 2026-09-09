package grpc.hw1;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class WordServer {

    private Server server;

    public void start() throws IOException {

        server = ServerBuilder
                .forPort(50051)
                .addService(new WordServiceImpl())
                .build()
                .start();

        System.out.println("gRPC Server started on port 50051");
    }

    private static class WordServiceImpl
            extends WordServiceGrpc.WordServiceImplBase {

    }
}

