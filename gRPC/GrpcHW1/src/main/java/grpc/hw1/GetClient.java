package grpc.hw1;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GetClient {

    public static void main(String[] args) {

        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress("localhost", 50051)
                        .usePlaintext()
                        .build();

        WordServiceGrpc.WordServiceBlockingStub stub =
                WordServiceGrpc.newBlockingStub(channel);

        GetWordRequest request =
                GetWordRequest.newBuilder()
                        .setClientName("GetClient")
                        .build();

        GetWordResponse response =
                stub.getWord(request);

        System.out.println(response.getWords());

        channel.shutdown();
    }
}