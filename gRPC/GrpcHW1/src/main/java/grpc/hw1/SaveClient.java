package grpc.hw1;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class SaveClient {

    public static void main(String[] args) {

        // 채널정보 준비
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress("localhost", 50051)
                        .usePlaintext()
                        .build();
        // 채널정보로 stub 생성
        WordServiceGrpc.WordServiceBlockingStub stub =
                WordServiceGrpc.newBlockingStub(channel);


        // 스캐너
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter word to save.");
        System.out.println("Enter 0 to exit.");

        while (true) {

            String word = scanner.next();

            if (word.equals("0")) {
                break;
            }

            // 리퀘스트 준비
            AddWordRequest request =
                    AddWordRequest.newBuilder()
                            .setWord(word)
                            .build();

            // 준비된 request로 실제 요청 및 response로 응답 저장
            AddWordResponse response =
                    stub.addWord(request);

            System.out.println(response.getMessage());
        }

        scanner.close();
        channel.shutdown();
    }
}