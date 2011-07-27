/*************************************************************************************
 * Copyright (c) 2008-2011 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.jboss.tools.as.sourcelookup.ui.browsers;

import org.eclipse.debug.core.sourcelookup.ISourceContainer;
import org.eclipse.debug.core.sourcelookup.ISourceLookupDirector;
import org.eclipse.debug.ui.sourcelookup.AbstractSourceContainerBrowser;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.jboss.tools.as.sourcelookup.containers.JBossSourceContainer;

/**
 * 
 * @author snjeza
 *
 */
public class JBossSourceContainerBrowser extends AbstractSourceContainerBrowser {
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.internal.ui.sourcelookup.ISourceContainerBrowser#createSourceContainers(org.eclipse.swt.widgets.Shell, org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public ISourceContainer[] addSourceContainers(Shell shell, ISourceLookupDirector director) {
		ISourceContainer[] containers = new ISourceContainer[1];
		String path = getHomePath(shell);
		if (path != null) {
			containers[0] = new JBossSourceContainer(path);
			return containers;
		}
		return new ISourceContainer[0];
	}

	private String getHomePath(Shell shell) {
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setMessage("Choose JBoss AS Home:");
		String path = dialog.open();
		return path;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.sourcelookup.AbstractSourceContainerBrowser#canEditSourceContainers(org.eclipse.debug.core.sourcelookup.ISourceLookupDirector, org.eclipse.debug.core.sourcelookup.ISourceContainer[])
	 */
	public boolean canEditSourceContainers(ISourceLookupDirector director, ISourceContainer[] containers) {
		return containers.length == 1 && JBossSourceContainer.TYPE_ID.equals(containers[0].getType().getId());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.sourcelookup.AbstractSourceContainerBrowser#editSourceContainers(org.eclipse.swt.widgets.Shell, org.eclipse.debug.core.sourcelookup.ISourceLookupDirector, org.eclipse.debug.core.sourcelookup.ISourceContainer[])
	 */
	public ISourceContainer[] editSourceContainers(Shell shell, ISourceLookupDirector director, ISourceContainer[] containers) {
		if (containers.length == 1 && JBossSourceContainer.TYPE_ID.equals(containers[0].getType().getId()) ) {
			JBossSourceContainer c = (JBossSourceContainer)containers[0];
			DirectoryDialog dialog = new DirectoryDialog(shell);
			dialog.setMessage("Choose JBoss AS Home:");
			if (c != null && c.getHomePath() != null) {
				dialog.setFilterPath(c.getHomePath());
			}
			String path = dialog.open();
			if(path !=null) {
					containers[0].dispose();
					return new ISourceContainer[]{ new JBossSourceContainer(path)};			
			}
		}
		return containers;
	}
	
}
