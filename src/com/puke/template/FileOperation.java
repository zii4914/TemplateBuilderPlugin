//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import com.puke.template.util.FileUtil;
import java.io.File;
import java.util.Iterator;

public class FileOperation extends Operation {
	public FileOperation(Target target) {
		super(target);
	}

	public void execute() {
		String packageName = TemplateHelper.getPackageName(this.target.getManifest());
		this.writeJavaFile(packageName);
		this.writeLayoutFile(packageName);
		this.writeDrawableFile(packageName);
	}

	private void writeJavaFile(String packageName) {
		String packagePath = TemplateHelper.getPackagePath(packageName);
		Iterator var3 = this.target.getJavaFiles().iterator();

		while(var3.hasNext()) {
			String javaFile = (String)var3.next();
			String originContent = FileUtil.getContent(javaFile);
			String newContent = originContent.replace(packageName, "${packageName}");
			String relativePath = javaFile.split(packagePath, 2)[1];
			String templateRelativePath = String.format("root/src/app_package/%s.ftl", relativePath);
			FileUtil.writeFile(this.getTemplatePath(templateRelativePath), newContent);
		}

	}

	private void writeLayoutFile(String packageName) {
		Iterator var2 = this.target.getLayoutFiles().iterator();

		while(var2.hasNext()) {
			String layoutFile = (String)var2.next();
			String originContent = FileUtil.getContent(layoutFile);
			String newContent = originContent.replace(packageName, "${packageName}");
			String relativePath = layoutFile.split("src/main/res/layout/", 2)[1];
			String templateRelativePath = String.format("root/res/layout/%s.ftl", relativePath);
			FileUtil.writeFile(this.getTemplatePath(templateRelativePath), newContent);
		}

	}

	private void writeDrawableFile(String packageName) {
		Iterator var2 = this.target.getDrawableFiles().iterator();

		while(var2.hasNext()) {
			String drawableFile = (String)var2.next();
			String relativePath = drawableFile.split("src/main/res/", 2)[1];
			String templateRelativePath = String.format("root/res/%s.ftl", relativePath);
			File templateAbsolutePath = this.getTemplatePath(templateRelativePath);
			if (drawableFile.endsWith(".xml")) {
				String originContent = FileUtil.getContent(drawableFile);
				String newContent = originContent.replace(packageName, "${packageName}");
				FileUtil.writeFile(templateAbsolutePath, newContent);
			} else {
				FileUtil.copy2File(new File(drawableFile), templateAbsolutePath);
			}
		}

	}

	protected String getFileName() {
		return null;
	}
}
