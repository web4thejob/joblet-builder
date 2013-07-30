package org.web4thejob.module;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MyJoblet extends AbstractJoblet {

	@Override
	public int getOrdinal() {
		// TODO Auto-generated method stub
		return 99;
	}

	@Override
	public boolean isInstalled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getSchemas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBasePackage() {
		// TODO Auto-generated method stub
		return null;
	}

}
