/*
 *  GeoBatch - Open Source geospatial batch processing system
 *  http://geobatch.geo-solutions.it/
 *  Copyright (C) 2007-2012 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 *  GPLv3 + Classpath exception
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.geosolutions.geobatch.task;

import it.geosolutions.filesystemmonitor.monitor.FileSystemEvent;
import it.geosolutions.geobatch.catalog.impl.BaseService;
import it.geosolutions.geobatch.flow.event.action.ActionService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comments here ...
 * 
 * @author Daniele Romagnoli, GeoSolutions S.a.S.
 */
public class TaskExecutorService extends BaseService implements
        ActionService<FileSystemEvent, TaskExecutorConfiguration> {

    private final static Logger LOGGER = Logger.getLogger(TaskExecutorService.class.toString());

    public TaskExecutorService(String id, String name, String description) {
        super(id, name, description, true);
    }

    // private TaskExecutorService() {
    // super(true);
    // }

    public boolean canCreateAction(TaskExecutorConfiguration configuration) {
        return true;
    }

    public TaskExecutor createAction(final TaskExecutorConfiguration configuration) {
        try {
            return new TaskExecutor(configuration);
        } catch (IOException e) {
            if (LOGGER.isLoggable(Level.INFO))
                LOGGER.log(Level.INFO, e.getLocalizedMessage(), e);
            return null;
        }
    }

}
