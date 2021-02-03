using System.Collections.Generic;
using System.Linq;
using niuz.application.finance;

namespace niuz.application.fixtures
{
    public class InMemoryContracts : IContractRepository
    {
        private readonly List<Contract> contracts = new List<Contract>();

        public void Add(Contract contract)
        {
            contracts.Add(contract);
        }

        public IEnumerable<Contract> OwnedBy(string authorId)
        {
            return contracts
                .Where(c => c.IsOwnedBy(authorId))
                .Select(c => c);
        }
    }
}