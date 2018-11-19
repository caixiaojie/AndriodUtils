package com.yjhs.cbsd_commonlibrary.remote;

import java.io.File;

/**
 * 上传文件
 * 
 * @author calvin
 */
public class FormFile {
	// 文件数据
	private byte[] fileData;
	// 文件对象
	private File file;
	/* 文件名称 */
	private String filename;
	// 请求参数名称，类似于 <input type="file" name="file1" /> 中的 "file1"
	private String parameterName;
	// 内容类型
	private String contentType = "application/octet-stream";

	public FormFile() {
		super();
	}
	public FormFile(String filePath) {
		// /storage/sdcard0/CustomMaintain/Images/photo_9209e8f1-6e8f-46b9-ad84-2ecc8864d937.png
		this(filePath.substring(filePath.lastIndexOf("/") + 1), new File(filePath), "file", null);
	}
	public FormFile(String filePath, String parameterName) {
		// /storage/sdcard0/CustomMaintain/Images/photo_9209e8f1-6e8f-46b9-ad84-2ecc8864d937.png
		this(filePath.substring(filePath.lastIndexOf("/") + 1), new File(filePath), parameterName, null);
	}

	public FormFile(String filename, byte[] fileData, String parameterName) {
		this(filename, fileData, parameterName, null);
	}

	public FormFile(String filename, File file, String parameterName) {
		this(filename, file, parameterName, null);
	}

	public FormFile(String filename, byte[] fileData, String parameterName, String contentType) {
		this.fileData = fileData;
		this.filename = filename;
		this.parameterName = parameterName;
		if (contentType != null) {
			this.contentType = contentType;
		}
	}

	public FormFile(String filename, File file, String parameterName, String contentType) {
		this.filename = filename;
		this.parameterName = parameterName;
		this.file = file;
		if (contentType != null) {
			this.contentType = contentType;
		}
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}