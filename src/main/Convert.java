package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Convert {
	private String filepath = "";
	private String outpath = "";
	private String output = "";
	private int ut, ti, j9, so, pdy, au, af, pi, fu, rp, sn, tc, pa, wc, sc,
			ab;

	public void setfilepath(String replace) {
		filepath = replace;
	}

	public String convert() {
		if (!filepath.toLowerCase().endsWith("xls")
				&& !filepath.toLowerCase().endsWith("xlsx")) {
			return "来源文件格式错误!";
		}

		File efile = new File(filepath);
		try {
			Workbook wb = Workbook.getWorkbook(efile);
			Sheet sheet = wb.getSheet(0);
			for (int cl = 0; cl < sheet.getColumns(); cl++) {// 获取excel文件的列
				Cell ecell = sheet.getCell(cl, 0);
				String ebiao = ecell.getContents().toString().trim()
						.toLowerCase();
				if (ebiao.equals("principalinvestigator")) {
					au = cl;
					af = cl;
				}
				if (ebiao.equals("title"))
					ti = cl;
				if (ebiao.equals("program(s)"))
					so = cl;
				if (ebiao.equals("abstract"))
					ab = cl;
				if (ebiao.equals("programmanager"))
					rp = cl;
				if (ebiao.equals("organization"))
					fu = cl;
				if (ebiao.equals("awardedamounttodate"))
					tc = cl;
				if (ebiao.equals("state"))
					pi = cl;
				if (ebiao.equals("organizationstreet"))
					pa = cl;// 这里注意合并
				if (ebiao.equals("enddate"))
					sn = cl;
				if (ebiao.equals("nsforganization"))
					j9 = cl;
				if (ebiao.equals("startdate"))
					pdy = cl;
				if (ebiao.equals("programelementcode(s)"))
					wc = cl;
				if (ebiao.equals("programreferencecode(s)"))
					sc = cl;
				if (ebiao.equals("awardnumber"))
					ut = cl;

			}
			File txt = new File(outpath + efile.getName().replace(".", "")
					+ ".txt");
			File win = new File(outpath + efile.getName().replace(".", "")
					+ "win.txt");
			if (txt.exists()) {
				txt.delete();
			}
			if (win.exists()) {
				win.delete();
			}
			txt.createNewFile();
			win.createNewFile();
			FileWriter fw = new FileWriter(txt);
			FileWriter fw1 = new FileWriter(win);
			fw.write("FN Thomson Reuters Web of Science™" + "\n" + "VR 1.0"
					+ "\n");
			fw1.write("PT" + "\t" + "AU" + "\t" + "BA" + "\t" + "BE" + "\t"
					+ "GP" + "\t" + "AF" + "\t" + "BF" + "\t" + "CA" + "\t"
					+ "TI" + "\t" + "SO" + "\t" + "SE" + "\t" + "BS" + "\t"
					+ "LA" + "\t" + "DT" + "\t" + "CT" + "\t" + "CY" + "\t"
					+ "CL" + "\t" + "SP" + "\t" + "HO" + "\t" + "DE" + "\t"
					+ "ID" + "\t" + "AB" + "\t" + "C1" + "\t" + "RP" + "\t"
					+ "EM" + "\t" + "RI" + "\t" + "OI" + "\t" + "FU" + "\t"
					+ "FX" + "\t" + "CR" + "\t" + "NR" + "\t" + "TC" + "\t"
					+ "Z9" + "\t" + "PU" + "\t" + "PI" + "\t" + "PA" + "\t"
					+ "SN" + "\t" + "EI" + "\t" + "BN" + "\t" + "J9" + "\t"
					+ "JI" + "\t" + "PD" + "\t" + "PY" + "\t" + "VL" + "\t"
					+ "IS" + "\t" + "PN" + "\t" + "SU" + "\t" + "SI" + "\t"
					+ "MA" + "\t" + "BP" + "\t" + "EP" + "\t" + "AR" + "\t"
					+ "DI" + "\t" + "D2" + "\t" + "PG" + "\t" + "WC" + "\t"
					+ "SC" + "\t" + "GA" + "\t" + "UT" + "\t" + "PM"+"\n");
			
			for (int rw = 1; rw < sheet.getRows(); rw++) {
				// 写文件
				// 先转换成txt格式
				fw.write("PT" + "\n");
				fw.write("AU " + sheet.getCell(au, rw).getContents() + "\n");
				fw.write("BA" + "\n" + "BE" + "\n" + "GP" + "\n");
				fw.write("AF " + sheet.getCell(af, rw).getContents() + "\n");
				fw.write("BF" + "\n" + "CA" + "\n" + "TI "
						+ sheet.getCell(ti, rw).getContents() + "\n");
				fw.write("SO " + sheet.getCell(so, rw).getContents() + "\n"
						+ "SE" + "\n" + "BS" + "\n" + "LA" + "\n" + "DT" + "\n"
						+ "CT" + "\n" + "CY" + "\n" + "CL" + "\n" + "SP" + "\n"
						+ "HO" + "\n" + "DE" + "\n" + "ID" + "\n");
				fw.write("AB " + sheet.getCell(ab, rw).getContents() + "\n"
						+ "C1" + "\n");
				fw.write("RP " + sheet.getCell(rp, rw).getContents() + "\n"
						+ "EM" + "\n" + "RI" + "\n" + "OI" + "\n");
				fw.write("FU " + sheet.getCell(fu, rw).getContents() + "\n"
						+ "FX" + "\n" + "CR" + "\n" + "NR" + "\n");
				String ward = sheet.getCell(tc, rw).getContents();
				Long tm;
				if(ward.length()==0) tm =(long) 0;
				else{
					ward = ward.trim().substring(1,ward.length()-3).replaceAll(",", "");
					tm = Long.parseLong(ward);
				}
				
//				int tc  = convetdollar(ward);
				fw.write("TC "
						+ tm + "\n" + "Z9" + "\n"
						+ "PU" + "\n");
				fw.write("PI " + sheet.getCell(pi, rw).getContents() + "\n");
				fw.write("PA " + sheet.getCell(pa, rw).getContents() + ", "
						+ sheet.getCell(pa + 1, rw).getContents() + ", "
						+ sheet.getCell(pa + 2, rw).getContents() + "\n" + "SN"
						+ "\n" + "EI" + "\n" + "BN" + "\n");
				fw.write("J9 " + sheet.getCell(j9, rw).getContents() + "\n"
						+ "JI" + "\n");
				String yd = sheet.getCell(pdy, rw).getContents();
				fw.write("PD " + yd.substring(0, yd.lastIndexOf("/")) + "\n"
						+ "PY "
						+ yd.substring(yd.lastIndexOf("/")+1, yd.length())
						+ "\n" + "VL" + "\n" + "IS" + "\n" + "PN" + "\n" + "SU"
						+ "\n" + "SI" + "\n" + "MA" + "\n" + "BP" + "\n" + "EP"
						+ "\n" + "AR" + "\n" + "DI" + "\n" + "D2" + "\n" + "PG"
						+ "\n");
				fw.write("WC " + sheet.getCell(wc, rw).getContents() + "\n"
						+ "SC " + sheet.getCell(sc, rw).getContents() + "\n"
						+ "GA" + "\n");
				fw.write("UT " + sheet.getCell(ut, rw).getContents() + "\n"
						+ "PM" + "\n\n");
				//写win格式的文本
				fw1.write("" + "\t");
				fw1.write(sheet.getCell(au, rw).getContents() + "\t");
				fw1.write("" + "\t" + "" + "\t" + "" + "\t");
				fw1.write(sheet.getCell(af, rw).getContents() + "\t");
				fw1.write("" + "\t" + "" + "\t" + sheet.getCell(ti, rw).getContents() + "\t");
				fw1.write( sheet.getCell(so, rw).getContents() + "\t"
						+ "" + "\t" + "" + "\t" + "" + "\t" + "" + "\t"
						+ "" + "\t" + "" + "\t" + "" + "\t" + "" + "\t"
						+ "" + "\t" + "" + "\t" + "" + "\t");
				fw1.write(sheet.getCell(ab, rw).getContents() + "\t"
						+ "" + "\t");
				fw1.write(sheet.getCell(rp, rw).getContents() + "\t"
						+ "" + "\t" + "" + "\t" + "" + "\t");
				fw1.write(sheet.getCell(fu, rw).getContents() + "\t"
						+ "" + "\t" + "" + "\t" + "" + "\t");

				fw1.write(tm + "\t" + "" + "\t"
						+ "" + "\t");
				fw1.write(sheet.getCell(pi, rw).getContents() + "\t");
				fw1.write(sheet.getCell(pa, rw).getContents() + ", "
						+ sheet.getCell(pa + 1, rw).getContents() + ", "
						+ sheet.getCell(pa + 2, rw).getContents() + "\t" + ""
						+ "\t" + "" + "\t" + "" + "\t");
				fw1.write(sheet.getCell(j9, rw).getContents() + "\t"
						+ "" + "\t");
			
				fw1.write(yd.substring(0, yd.lastIndexOf("/")) + "\t"
						+ yd.substring(yd.lastIndexOf("/")+1, yd.length())
						+ "\t" + "" + "\t" + "" + "\t" + "" + "\t" + ""
						+ "\t" + "" + "\t" + "" + "\t" + "" + "\t" + ""
						+ "\t" + "" + "\t" + "" + "\t" + "" + "\t" + ""
						+ "\t");
				fw1.write(sheet.getCell(wc, rw).getContents() + "\t"
						+sheet.getCell(sc, rw).getContents() + "\t"
						+ "" + "\t");
				fw1.write(sheet.getCell(ut, rw).getContents() + "\t"
						+ "" + "\t\n");
				

			}
			fw.close();
			fw1.close();
			output = outpath + efile.getName().replace(".", "")
					+ ".txt"+"\n  和      "+outpath + efile.getName().replace(".", "")
					+ "win.txt"+"\n"+"转换成功！";
		} catch (BiffException e) {
			System.out.println("格式错误！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO错误！");
			e.printStackTrace();
		}
		return output;

	}

	/**
	 * 将美元转换成数值类型
	 * **/
	
	

	public void setOutPath(String replace) {
		outpath = replace+"\\\\";

	}

}
