package com.dant.app;

import com.dant.exception.ForbiddenExceptionMapper;
import com.dant.exception.InternalServerExceptionMapper;
import com.dant.exception.RuntimeExceptionMapper;
import com.dant.filter.GsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pitton on 2017-02-20.
 */
@ApplicationPath("")
public class App extends Application {

	@Override
	public Set<Object> getSingletons() {
		Set<Object> sets = new HashSet<>();
		sets.add(new TestEndpoint());
		sets.add(new UserController());
		return sets;
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> sets = new HashSet<>();
		sets.add(GsonProvider.class);
		sets.add(RuntimeExceptionMapper.class);
		sets.add(InternalServerExceptionMapper.class);
		sets.add(ForbiddenExceptionMapper.class);
		return sets;
	}
}
