import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		ServerIF server;
		
		Scanner sc = new Scanner(System.in);
		Integer orderNum;
		String stopWd = "exit";
		
		String in1;
		String in2;
		
		while(true) {
			welcomSay();
			orderNum = sc.nextInt();
			if(orderNum==0) break;
			
			try {
				server = (ServerIF)Naming.lookup("Server1");
				
				while (true){
					if (orderNum == 1) {
						System.out.println("정수 덧셈 (exit를 입력하면 종료)");
						in1 = sc.next();
						if (in1.equals(stopWd)) break; 
						int a = stringToInt(in1);
						
						in2 = sc.next();
						if (in2.equals(stopWd)) break; 
						int b = stringToInt(in2);

                        //Server와 ServerIF 에서 add관련을 지움
                        //System.out.println("Server's answer : " + server.add(a,b));
					} else {
						System.out.println("잘못된 번호입니다. 0~1번을 입력해주세요.");
						break;
					}
				}
			//-----------------예외 처리----------//
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void welcomSay() {
		System.out.println("----------------------------");
		System.out.println("입력한 번호에 대한 명령을 실행해줘여 ");
		System.out.println("0 : 프로그램 종료 ");
		System.out.println("1 : 정수 덧셈 ");
	}
	
	public static int stringToInt(String str) {
        // Integer.parseInt()가 문자를 정수로 바꿔주는 자바 내장 문법
        return Integer.parseInt(str); 
    }

}
