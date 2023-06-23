/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.operation.predstava;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Predstava;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author andelalausevic
 */
public class GetAllPredstava extends AbstractGenericOperation{
     private List<Predstava> predstave;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        predstave = repository.getAll((Predstava) param);
    }

    public List<Predstava> getPredstave() {
        return predstave;
    }
}
