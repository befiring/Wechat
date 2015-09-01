package com.javen.course.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SaveLog {

	public static void OutLogToFile(String filePath, String fileName, String logStr, boolean isopenOrClose)
	{
		// isopenOrClose = true;
		try
		{
			if( isopenOrClose == true )
			{
				if( filePath.equals("") )
				{
					filePath = "f:\\";
				}

				if( fileName.equals("") )
				{
					fileName = "log.txt";
				}

				String filePathName = filePath + fileName;

				// �ж��ļ����Ƿ����
				File filePathIsExists = new File(filePath);
				if( !filePathIsExists.exists() )
				{
					try
					{
						filePathIsExists.mkdir();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}

				// �ж��ļ��Ƿ����
				File fileNameIsExists = new File(filePathName);
				if( !fileNameIsExists.exists() )
				{
					try
					{
						fileNameIsExists.createNewFile();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					long fileSize = fileNameIsExists.length();
					if( fileSize >= 1024 * 1024 * 20 )
					{
						try
						{
							fileNameIsExists.delete();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

						if( !fileNameIsExists.exists() )
						{
							try
							{
								fileNameIsExists.createNewFile();
							}
							catch (IOException e)
							{
								e.printStackTrace();
							}
						}
					}
				}

				// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
				FileWriter writer = new FileWriter(filePathName, true);
				writer.write(logStr);
				writer.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
