package com.dddeurope.niuz.finance;

import java.util.List;

public interface ContractRepository {
    void add(Contract contract);
    List<Contract> ownedBy(String authorId);
}
