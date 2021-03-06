/*
 * This file is part of LibrePlan
 *
 * Copyright (C) 2009-2010 Fundación para o Fomento da Calidade Industrial e
 *                         Desenvolvemento Tecnolóxico de Galicia
 * Copyright (C) 2010-2011 Igalia, S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.zkoss.ganttz.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo Tilve Álvaro <ltilve@igalia.com>
 */
public class Milestone extends Task {

    public Milestone(ITaskFundamentalProperties fundamentalProperties) {
        super(fundamentalProperties);
    }

    private List<Task> tasks = new ArrayList<Task>();

    private TaskContainer owner;

    private boolean expanded = false;

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!this.expanded) {
            return;
        }
        for (Task task : tasks) {
            task.setVisible(true);
        }
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean canBeExplicitlyMoved() {
        return true;
    }

    public void setOwner(TaskContainer owner) {
        this.owner = owner;
    }

    public TaskContainer getOwner() {
        return this.owner;
    }

}
