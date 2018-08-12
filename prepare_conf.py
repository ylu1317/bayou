import os;
import re;
import sys;
import json;

def get_package(fname):
    """
    get the package this java file is in
    """
    with open(fname, "r", errors = "ignore") as infile:
        lines = infile.readlines();
        for line in lines:
            m = re.search('package\s+(.+?);', line);
            if m is not None:
                package = m.group(1);
                return package;

def gen_conf(pkg_set):
    """
    generate a bayou config using all the package names we gathered.
    """

    # read the config template
    with open("conf.json", "r") as infile:
        json_txt = infile.read();
        conf_json = json.loads(json_txt);

    for pkg in pkg_set:
        if not pkg.startswith("edu.rice"):
            conf_json["api-packages"].append(pkg);

    # convert the json back to string
    return json.dumps(conf_json, indent = 2);

def main(args):
    if len(args) < 2:
        sys.exit("""Usage: prepare_conf.py [dir name]

        This script is used to prepare a config file for running dom driver.
        It takes a dir name and generate a dom driver config file filled with package names.
        """);

    dirname = args[1];
    print("Generating config file for dir '%s'" % dirname);
    all_pkg = set();

    for dirpath, dirnames, filenames in os.walk(dirname):
        for fname in filenames:
            if fname.endswith(".java"):
                pkg = get_package(dirpath + "/" + fname);
                if pkg is not None:
                # print "package: '%s' from '%s'" %(pkg, fname);
                    all_pkg.add(pkg);
    
    print("Preparing config file..");
    conf_str = gen_conf(all_pkg);

    out_fname = dirname + "/" + dirname + ".conf";
    print("Writing config into '%s'" % out_fname);
    with open(out_fname, "w") as outfile:
        outfile.write(conf_str);

    print("Done.");
    
if __name__ == '__main__':
    main(sys.argv)
