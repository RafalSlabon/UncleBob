package payroll.union;

import java.util.HashMap;
import java.util.Map;

public class UnionMemberships {
    private static final Map<String, UnionMembership> UNION_MEMBERSHIPS = new HashMap<String, UnionMembership>();

    public static UnionMembership getByMemberId(String memberId) {
        for(UnionMembership um:UNION_MEMBERSHIPS.values()){
            if(um.memberId.equals(memberId))
                return um;
        }
        return null;
    }

    public static UnionMembership getByEmpId(String empId) {
        return UNION_MEMBERSHIPS.get(empId);
    }

    public static void addMembership(UnionMembership unionMembership) {
        UNION_MEMBERSHIPS.put(unionMembership.empId, unionMembership);
    }

    public static void cleanAll() {
        UNION_MEMBERSHIPS.clear();
    }

    public static void removeByEmpId(String empId) {
        UNION_MEMBERSHIPS.remove(empId);
    }

}
