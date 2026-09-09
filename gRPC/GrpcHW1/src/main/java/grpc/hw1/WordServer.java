package grpc.hw1;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

public class WordServer {

    private Server server;

    private static final List<String> wordList = new ArrayList<>();

    public void start() throws IOException {

        server = ServerBuilder
                .forPort(50051)
                .addService(new WordServiceImpl()) // 아래 서비스 클래스를 등록
                .build()
                .start();

        System.out.println("gRPC Server started on port 50051");
    }
    private void blockUntilShutdown() throws InterruptedException {
        // 서버가 종료되기 전까지 응답 기다리기
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {

        WordServer wordServer = new WordServer();

        wordServer.start();
        wordServer.blockUntilShutdown();
    }

    private static class WordServiceImpl
            extends WordServiceGrpc.WordServiceImplBase {

        @Override
        public void addWord(
                AddWordRequest request,
                StreamObserver<AddWordResponse> responseObserver) {

            //응답에서 스트링을 꺼내는 과정
            String word = request.getWord(); // .getWord()는 .proto에서 정의되어, AddWordRequest에서 자동생성된 getter 메서드

            wordList.add(word); // 위에서 생성한 리스트에 추가, RMI와 같은 로직으로 이부분들을 *비지니스 로직* 이라고한다고함

            System.out.println("Added word : " + word);

            AddWordResponse response =
                    AddWordResponse.newBuilder()
                            .setMessage("Added word : " + word)
                            .build();

            // 현재 이 addWord 메서드가 받은 gRPC 객체 responseObserver를 통해 클라이언트에게 응답을 보내는 과정
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void getWord(
                GetWordRequest request,
                StreamObserver<GetWordResponse> responseObserver) {

            String clientName = request.getClientName();

            System.out.println(
                    "[" + clientName + "] read the list"
            );

            GetWordResponse response =
                    GetWordResponse.newBuilder()
                            .setWords(wordList.toString())
                            .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    }
}

