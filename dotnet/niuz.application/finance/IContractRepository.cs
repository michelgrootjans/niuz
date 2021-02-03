using System.Collections.Generic;

namespace niuz.application.finance
{
    public interface IContractRepository
    {
        void Add(Contract contract);
        IEnumerable<Contract> OwnedBy(string authorId);
    }
}