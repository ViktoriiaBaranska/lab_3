package org.example;

import java.util.ArrayList;
import java.util.List;

/*
  @author   Viktoriia Baranska
  @project   lab3
  @class  CompanyServiceImpl
  @version  1.0.0
*/

public class CompanyServicelmpl implements ICompanyService {

    @Override
    public Company getTopLevelParent(Company child) {
        Company current = child;
        List<Company> visited = new ArrayList<>();

        while (current != null && current.getParent() != null) {
            if (visited.contains(current)) {
                throw new IllegalStateException("Circular reference detected in company hierarchy.");
            }
            visited.add(current);
            current = current.getParent();
        }

        return current;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        long totalEmployees = company.getEmployeeCount();

        for (Company child : companies) {
            if (child.getParent() != null && child.getParent().equals(company)) {
                totalEmployees += getEmployeeCountForCompanyAndChildren(child, companies);
            }
        }

        return totalEmployees;
    }
}
