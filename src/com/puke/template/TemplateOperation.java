//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

public class TemplateOperation extends TextOperation {
	public TemplateOperation(Target target) {
		super(target);
	}

	protected String handleText(String originContent) {
		this.newReplacementChain().append("moduleName", this.config.getModuleName()).append("moduleDesc", this.config.getModuleDesc()).append("moduleData", this.config.getModuleData()).append("moduleCategory", this.config.getModuleCategory());
		return super.handleText(originContent);
	}

	protected String getFileName() {
		return "template.xml";
	}
}
