package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.finance.Contract;
import com.dddeurope.niuz.finance.ContractRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContractRepository implements ContractRepository {
    private final List<Contract> contracts = new ArrayList<>();

    @Override
    public void add(Contract contract) {
        contracts.add(contract);
    }

    @Override
    public List<Contract> ownedBy(String authorId) {
        return this.contracts.stream()
                             .filter(c -> c.IsOwnedBy(authorId))
                             .collect(Collectors.toList());
    }
}
