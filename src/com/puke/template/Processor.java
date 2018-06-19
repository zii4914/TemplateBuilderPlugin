//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Processor {
	private final List<Operation> operations;
	private TemplateConfig config;

	public Processor(Target target) {
		this(target, FileOperation.class, CoverOperation.class, RecipeOperation.class, GlobalsOperation.class, TemplateOperation.class, BuildGradleOperation.class, AndroidManifestOperation.class);
	}

	public Processor(Target target, Class... operationClss) {
		this.operations = new ArrayList();
		Class[] var3 = operationClss;
		int var4 = operationClss.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			Class operationCls = var3[var5];

			try {
				Constructor<? extends Operation> constructor = operationCls.getConstructor(Target.class);
				Operation operation = (Operation)constructor.newInstance(target);
				this.operations.add(operation);
			} catch (Exception var9) {
				throw new RuntimeException(var9.getMessage(), var9);
			}
		}

		Iterator var10 = this.operations.iterator();

		while(var10.hasNext()) {
			Operation operation = (Operation)var10.next();
			operation.onInit(this);
		}

	}

	public <T extends Operation> T getOperation(Class<T> operationCls) {
		Iterator var2 = this.operations.iterator();

		Operation operation;
		do {
			if (!var2.hasNext()) {
				return null;
			}

			operation = (Operation)var2.next();
		} while(!operationCls.isInstance(operation));

		return (T) operation;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	public void setTemplateConfig(TemplateConfig config) {
		this.config = config;
		Iterator var2 = this.operations.iterator();

		while(var2.hasNext()) {
			Operation operation = (Operation)var2.next();
			operation.onConfigLoaded(config);
		}

	}

	public void process() {
		this.createModuleDir();
		Iterator var1 = this.operations.iterator();

		while(var1.hasNext()) {
			Operation operation = (Operation)var1.next();
			operation.execute();
		}

	}

	private void createModuleDir() {
		File templatePath = this.config.getTemplatePath();
		if (templatePath == null) {
			throw new RuntimeException("The configuration ");
		} else if (!templatePath.exists() && !templatePath.mkdirs()) {
			throw new RuntimeException("Can't create folder:\n" + templatePath);
		}
	}
}
