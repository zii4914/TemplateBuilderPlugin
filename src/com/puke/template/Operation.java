//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import com.puke.template.util.FileUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class Operation {
	protected final Target target;
	protected TemplateConfig config;
	protected String fileName;
	private boolean enable = true;
	private byte[] buffer;

	public Operation(Target target) {
		this.target = target;
		this.fileName = this.getFileName();
	}

	protected void onInit(Processor processor) {
		Field[] var2 = this.getClass().getDeclaredFields();
		int var3 = var2.length;

		for(int var4 = 0; var4 < var3; ++var4) {
			Field field = var2[var4];
			Class fieldType = field.getType();
			if (Operation.class.isAssignableFrom(fieldType)) {
				Operation operation = processor.getOperation(fieldType);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}

				try {
					field.set(this, operation);
				} catch (IllegalAccessException var9) {
					var9.printStackTrace();
				}
			}
		}

	}

	protected void onConfigLoaded(TemplateConfig config) {
		this.config = config;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void execute() {
		if (this.enable) {
			File targetFile = this.getTemplatePath(this.fileName);
			FileUtil.save2File(this.getCurrentContent(), targetFile);
		}

	}

	protected abstract String getFileName();

	public void setCurrentContent(byte[] content) {
		this.buffer = content;
	}

	public byte[] getCurrentContent() {
		if (this.buffer == null) {
			byte[] temp = TemplateHelper.getOriginBytes(this.fileName);
			if (temp == null) {
				throw new RuntimeException("Read origin file failure.");
			}

			this.buffer = this.handleByte(temp);
			if (this.buffer == null) {
				this.buffer = "".getBytes();
			}
		}

		return this.buffer;
	}

	protected byte[] handleByte(byte[] bytes) {
		return bytes;
	}

	public File getTemplatePath(String relativePath) {
		String templatePath = this.config.getTemplatePath().getAbsolutePath().replace('\\', '/');
		return new File(templatePath + "/" + relativePath);
	}

	public String getActualFileName() {
		String actualFileName = this.getFileName();
		int dividerIndex = actualFileName.indexOf(47);
		if (dividerIndex > -1 && !actualFileName.endsWith("/")) {
			actualFileName = actualFileName.substring(dividerIndex + 1, actualFileName.length());
		}

		return actualFileName;
	}
}
