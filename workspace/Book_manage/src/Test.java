import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	
	static Scanner scan = new Scanner(System.in);
	static String filePath="C:\\test\\book.txt";
	static int book_count;
	static int lineNumber;
	
	public static void main(String[] args) throws IOException {
		
		printmenu();
		
		int choice = -1;
	
		while ( choice != 0)
		{
			choice = inputchoice();
			switch(choice)
			{
			
			case 1:
				printbooks();
				break;
			case 2:
				search_all_book();
				break;
			case 3:
				insertbook();
				break;
			case 4:
				deletebook();
				break;
			case 0:
				System.out.println("종료합니다.");
				System.exit(0);
				break;
			default :
				System.out.println("다시 입력해 주세요");
			}
		}
	
	}

	private static int inputchoice() {
		
		int choice=-1;
		
		System.out.println("선택 하세요 :");
		choice = scan.nextInt();
		
		System.out.println("선택한 메뉴 : " + choice);
		System.out.println();
		return choice;
	}
	
	private static void printmenu()
	{
		System.out.println("도서 관리 프로그램입니다.");
		System.out.println("1. 전체 목록 출력");
		System.out.println("2. 도서 검색");
		System.out.println("3. 신규 도서 추가");
		System.out.println("4. 노후 도서 폐기");
		System.out.println("0. 프로그램 종료");
	}

	private static void deletebook() throws IOException{
		String tmpFilePath = filePath +".tmp";
		int count = 1;
	 
		 System.out.println("삭제하실 책 번호를 입력해주세요(행 번호 입력) : " );
		 int booknumber = scan.nextInt();
		      
		 System.out.println("입력하신 책 번호 : "+booknumber);
		   
		 BufferedReader br = new BufferedReader(new FileReader(filePath));
		 BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
		      
		 String str="";
		      
		 while((str = br.readLine()) != null){
			 if(count != booknumber){
		     bw.write(str);
		     bw.write("\r\n");
		     }
			 count ++;
			 }
		 bw.close();
		 br.close();
		      
		 FileInputStream fis = new FileInputStream(tmpFilePath);
		 FileOutputStream fos = new FileOutputStream(filePath);
		      
		 int data = 0;
		 while((data = fis.read()) != -1){
		 fos.write(data);
		 }
		 fis.close();
		 fos.close();
		      
		 File f = new File(tmpFilePath);
		 f.deleteOnExit();
		 }

	private static void insertbook() throws IOException {
		System.out.println("추가할 책의 정보를 입력해 주세요 ");
		
		scan.nextLine();
		System.out.println("책 이름 : ");
		String name = scan.nextLine();

		System.out.println("저    자 : ");
		String Author = scan.nextLine();

		System.out.println("출판사 : ");
		String publisher = scan.nextLine();

		System.out.println("가    격 : ");
		String price = scan.nextLine();

		System.out.println("책 이름 "+name);
		System.out.println("책 저자 "+Author);
		System.out.println("책 출판사 "+publisher);
		System.out.println("책 가격"+price);
		
		System.out.println("입력한 값이 맞습니까? 1(Y)/2(N)" );
		System.out.println("메뉴로 이동하시려면 3을 입력해 주세요" );
		int input;
		input = scan.nextInt();
		
		if(input == 2)
		{
			System.out.println("다시 입력합니다.");
			insertbook();
		}
		else if(input == 3)
		{
			System.out.println("메뉴로 이동합니다.");
			inputchoice();
		}
		else
		{		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
		
		bw.write(name + "\t" + Author + "\t" + publisher + "\t" + price);
		bw.newLine();
		bw.close();
		System.out.println("입력 되었습니다.");
		}
		
	}

	private static void printbooks() throws FileNotFoundException {
		scan.nextLine();
		System.out.println("책 출력");
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		//경로에 있는 txt파일 읽기
		lineNumber = 1;
		//행 번호
		book_count = 0;
		
		String str = "";
		try {
			while((str=br.readLine())!=null)
			{
				System.out.println(lineNumber + " . " + str);
				book_count ++; //입력된 총 권수 표시
				lineNumber ++; //라인 별 번호 부여
			}
			System.out.println("총 입력된 권 수 : " + book_count);
		} catch (IOException e) {
			System.out.println("책 정보를 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}

	private static void search_all_book() throws FileNotFoundException {
		
		scan.nextLine();
		
		System.out.print("키워드를 입력해 주세요 : ");
		String Keyword = scan.nextLine();
		lineNumber = 1;
		book_count = 0;
		
		String str = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		

		try {
			while((str=br.readLine())!=null)
			{
				if(str.contains(Keyword)){
					System.out.println( lineNumber + " . " + str);
					book_count ++;
				}
				lineNumber ++;
			}
			System.out.println("일치하는 자료 수 : " + book_count);
			br.close();
		} catch (IOException e) {
			System.out.println("책 정보를 찾을 수 없습니다.");
			e.printStackTrace();
		}
		
	}

}