/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.PSServer.operation.predstava;

import java.util.List;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSServer.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.PSServer.repository.Repository;

/**
 * Klasa za vracanje svih predstava iz baze podataka
 * Nasledjuje apstraktnu klasu AbstractGenericOperation i implementira sve njene metode.
 * @author andelalausevic
 */
public class GetAllPredstava extends AbstractGenericOperation{
    /**
     * predstave su lista predstavi, lista nije inicijalizovana
     */
     private List<Predstava> predstave;

     public GetAllPredstava(){
         
     }
   public GetAllPredstava(Repository repo) {
        repository=repo;
    }

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        predstave = repository.getAll((Predstava) param);
    }

    /**
     * metoda vraca listu predstavi
     * @return predstave lista predstavi
     */
    public List<Predstava> getPredstave() {
        return predstave;
    }
}
