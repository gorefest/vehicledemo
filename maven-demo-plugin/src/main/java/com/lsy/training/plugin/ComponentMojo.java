package com.lsy.training.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;


@Mojo(name="component")
public class ComponentMojo extends AbstractMojo {

	@Component
	MavenProject mavenProject;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Maven Project Artifact Id "+mavenProject.getArtifactId());
	}

}
