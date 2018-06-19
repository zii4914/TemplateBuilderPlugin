//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import java.io.File;

public enum DefaultTemplateConfig implements TemplateConfig {
	INSTANCE;

	private DefaultTemplateConfig() {
	}

	public File getTemplatePath() {
		return new File("/Users/zijiao/Documents/WorkSpace/Idea/AS_Template/test");
	}

	public String getModuleName() {
		return "HelloWorld";
	}

	public String getModuleDesc() {
		return "This is a desc of hello world.";
	}

	public String getModuleData() {
		return null;
	}

	public String getModuleCategory() {
		return "Activity";
	}
}
