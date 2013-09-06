package com.lsy.training.plugin;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name="snapshot-nagger")
public class SnapshotNaggerMojo extends AbstractMojo{

	@Component
	MavenProject mavenProject;

	@Parameter(defaultValue="${overrideNagger}",property="overrideNagger")
	String overrideNagger; 
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		boolean override = "true".equals(overrideNagger);
		
		Collection<Dependency> dependencies = mavenProject.getDependencies();
		Collection<Dependency> snaps = new ArrayList<Dependency>();
		
		for (Dependency dependency : dependencies) {
			if (dependency.getVersion().endsWith("-SNAPSHOT")
					&& !mavenProject.getGroupId().equals(dependency.getGroupId())) {
				snaps.add(dependency);
			}
		}
		
		if (!snaps.isEmpty()) {
			for (Dependency dependency : snaps) {
				if (override) {
					getLog().warn("Warning! Snapshot dep found "+dependency.toString());
				} else {
					getLog().error("Error! Snapshot dep found "+dependency.toString());
				}
			}
			if (!override) {
				throw new MojoFailureException("SNAPSHOT DEPENDENCIES WERE FOUND!");
			}
		}
		
		
	}
	
	
	
}
