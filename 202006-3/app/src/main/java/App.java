import java.io.*;
import java.util.*;

/**
 * @author ethever.eth
 */

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        // map key:角色名字 value：角色对象 ，用来根据名字快速定位
        Map<String, role> map = new HashMap<>(100000);
        // 添加角色模块
        while (n-- > 0) {
            String name = in.next();
            role r = new role(name);
            int nv = in.nextInt();
            // 将操作、资源类型、资源名称，分别加入对应的Set集合
            while (nv-- > 0) {
                String op = in.next();
                r.setop.add(op);
            }
            int no = in.nextInt();
            while (no-- > 0) {
                String doc = in.next();
                r.setdoc.add(doc);
            }
            int nn = in.nextInt();
            while (nn-- > 0) {
                String file = in.next();
                r.setfile.add(file);
            }

            map.put(name, r);
        }
        // 存用户
        Map<String, cont> people = new HashMap<>(1000);
        // 存用户组
        Map<String, cont> group = new HashMap<>(1000);
        // 添加用户和用户组模块
        while (m-- > 0) {
            String name = in.next();
            int ns = in.nextInt();
            while (ns-- > 0) {
                String f = in.next();
                String s = in.next();
                // 判别类型分别加入，如果记录过就直接添加到集合中
                if (f.compareTo("g") == 0) {
                    if (group.containsKey(s)) {
                        group.get(s).role.add(name);
                    } else {
                        group.put(s, new cont(name));
                    }
                } else {
                    if (people.containsKey(s)) {
                        people.get(s).role.add(name);
                    } else {
                        people.put(s, new cont(name));
                    }
                }

            }
        }
        // 实时判断模块
        while (q-- > 0) {
            String name = in.next();
            int ng = in.nextInt();
            String[] ss = new String[ng];
            for (int i = 0; i < ng; i++) {
                ss[i] = in.next();
            }
            String op = in.next();
            String doc = in.next();
            String file = in.next();

            // 判断用户名是否可以通过
            boolean f = false;
            if (people.containsKey(name)) {
                for (String rolename : people.get(name).role) {
                    if (map.containsKey(rolename)) {
                        role r = map.get(rolename);
                        if (r.setop.contains(op) || r.setop.contains("*")) {
                            if (r.setdoc.contains(doc) || r.setdoc.contains("*")) {
                                if (r.setfile.isEmpty() || r.setfile.contains(file)) {
                                    f = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if (f) {
                System.out.println(1);
                continue;
            }
            // 判断用户组是否可以通过
            for (String s : ss) {
                boolean ff = false;
                if (group.containsKey(s)) {
                    // 遍历用户组的角色
                    for (String rolename : group.get(s).role) {
                        if (map.containsKey(rolename)) {
                            role r = map.get(rolename);
                            if (r.setop.contains(op) || r.setop.contains("*")) {
                                if (r.setdoc.contains(doc) || r.setdoc.contains("*")) {
                                    if (r.setfile.isEmpty() || r.setfile.contains(file)) {
                                        // 判断成不成功的
                                        f = true;
                                        // 用来判断及时推出的
                                        ff = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (ff) {
                    break;
                }
            }
            System.out.println(f ? 1 : 0);
        }

    }
}

class role {
    String name;
    Set<String> setop = new HashSet<>();
    Set<String> setdoc = new HashSet<>();
    Set<String> setfile = new HashSet<>();

    role(String name) {
        this.name = name;
    }

}

class cont {
    Set<String> role = new HashSet<>();
    cont(String rolename) {
        role.add(rolename);
    }
}
