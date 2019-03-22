package project02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tour {
	double distance = 10000;
	String text;
	int [] number;
	int n;
	Visitor[] a;

	public void start() {
		for (int i = 0; i < 7; i++) {
			text = "input" + i + ".txt";
			try {
				Scanner sfile = new Scanner(new File(text));
				n = sfile.nextInt();
				a = new Visitor[n];
				number=new int[n];
				for (int j = 0; j < n; j++) {
					a[j] = new Visitor(sfile.nextInt(), sfile.nextInt(),j);
				}
				sfile.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("파일이 존재하지 않습니다.");
			}
			distance = 10000;
			find_route(0,0);
			 System.out.println("answer:");
			System.out.println(distance);
			System.out.print("[");
			for (int j = 0; j < n; j++) {
				System.out.print(number[j]+" ");
			}
			System.out.println("]");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tour AS = new Tour();
		AS.start();
	}
	public void find_route(int k,double rjfl) {
		if (distance < rjfl)
			return;
		else if (k == n) {
			rjfl+=sum(k-1,0);
			if (distance > rjfl) {
				distance = rjfl;
				for(int i=0;i<n;i++)
					number[i]=a[i].num;
			}
		} else
			for (int i = k; i < n; i++) {
				sort(i, k);
				find_route(k + 1,rjfl+sum(k-1,k));
				sort(i, k);
			}
	}
	public void sort(int i, int k) {
		Visitor q;
		q = a[i];
		a[i] = a[k];
		a[k] = q;
	}

	public double sum(int pre, int next) {
		if(pre==-1)
			return 0;
		else
			return Math.sqrt((Math.pow(a[pre].x - a[next].x, 2) + Math.pow(a[pre].y - a[next].y, 2)));
	}
}