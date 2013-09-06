package com.lsy.training.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Read a parameter foobar from config and display it
 * 
 * @author marcus
 *
 */
@Mojo(name="parameter")
public class ParameterMojo extends AbstractMojo {

	@Parameter( property="foobar")
	String foobar;
	
	@Parameter(defaultValue="${foo}")
	String foo;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Parameter foobar was "+foobar);
		getLog().info("Parameter foo was "+foo);
	}

}
