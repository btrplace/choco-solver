/**
 *  Copyright (c) 1999-2011, Ecole des Mines de Nantes
 *  All rights reserved.
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the Ecole des Mines de Nantes nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package solver.constraints.nary;


import org.testng.Assert;
import org.testng.annotations.Test;
import solver.Solver;
import solver.constraints.Constraint;
import solver.constraints.nary.cnf.PropSat;
import solver.search.loop.monitors.SMF;
import solver.variables.BoolVar;
import solver.variables.VF;

/**
 * <br/>
 *
 * @author Charles Prud'homme
 * @since 12/07/13
 */
public class SatTest {

    @Test(groups = "1s")
    public void test1() {
        Solver solver = new Solver();
        PropSat psat = new PropSat(solver);
        BoolVar b1, b2;
        b1 = VF.bool("b1", solver);
        b2 = VF.bool("b2", solver);
        PropSat.addBoolEq(psat, b1, b2);
        Constraint<BoolVar, PropSat> sat = new Constraint<BoolVar, PropSat>(solver);
        sat.addPropagators(psat);
        solver.post(sat);
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 2);
    }

    @Test(groups = "1s")
    public void test2() {
        Solver solver = new Solver();
        PropSat psat = new PropSat(solver);
        BoolVar b1, b2;
        b1 = VF.bool("b1", solver);
        b2 = VF.bool("b2", solver);
        PropSat.addBoolNot(psat, b1, b2);
        Constraint<BoolVar, PropSat> sat = new Constraint<BoolVar, PropSat>(solver);
        sat.addPropagators(psat);
        solver.post(sat);
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 2);
    }

    @Test(groups = "1s")
    public void test3() {
        Solver solver = new Solver();
        PropSat psat = new PropSat(solver);
        BoolVar b1, b2;
        b1 = VF.bool("b1", solver);
        b2 = VF.bool("b2", solver);
        PropSat.addBoolLe(psat, b1, b2);
        Constraint<BoolVar, PropSat> sat = new Constraint<BoolVar, PropSat>(solver);
        sat.addPropagators(psat);
        solver.post(sat);
        SMF.log(solver, true, true);
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 1);
    }

    @Test(groups = "1s")
    public void test4() {
        Solver solver = new Solver();
        PropSat psat = new PropSat(solver);
        BoolVar b1, b2, r;
        b1 = VF.bool("b1", solver);
        b2 = VF.bool("b2", solver);
        r = VF.bool("r", solver);
        PropSat.addBoolIsEqVar(psat, b1, b2, r);
        Constraint<BoolVar, PropSat> sat = new Constraint<BoolVar, PropSat>(solver);
        sat.addPropagators(psat);
        solver.post(sat);
        solver.findAllSolutions();
        Assert.assertEquals(solver.getMeasures().getSolutionCount(), 4);
    }
}
