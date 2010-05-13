/*
 * This file is part of NavalPlan
 *
 * Copyright (C) 2009 Fundación para o Fomento da Calidade Industrial e
 *                    Desenvolvemento Tecnolóxico de Galicia
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

package org.navalplanner.business.planner.daos;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.navalplanner.business.common.daos.GenericDAOHibernate;
import org.navalplanner.business.planner.entities.DayAssignment;
import org.navalplanner.business.planner.entities.DerivedDayAssignment;
import org.navalplanner.business.resources.entities.Resource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@DayAssignment}
 *
 * @author Diego Pino García <dpino@igalia.com>
 * @author Manuel Rego Casasnovas <mrego@igalia.com>
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DayAssignmentDAO extends GenericDAOHibernate<DayAssignment, Long>
        implements IDayAssignmentDAO {

    @Override
    public void removeDerived(
            Collection<? extends DerivedDayAssignment> assignments) {
        for (DerivedDayAssignment each : assignments) {
            getSession().delete(each);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DayAssignment> listFilteredByDate(LocalDate init, LocalDate end) {
        Criteria criteria  = getSession().createCriteria(DayAssignment.class);
        if(init != null) {
            criteria.add(Restrictions.ge("day", init));
        }
        if(end != null) {
            criteria.add(Restrictions.le("day", end));
        }
        return criteria.list();
    }

    @Override
    public List<DayAssignment> findByResources(List<Resource> resources) {
        if (resources.isEmpty()) {
            return Collections.emptyList();
        }
        return getSession().createCriteria(DayAssignment.class).add(
                Restrictions.in("resource", resources)).list();
    }

}
