boolean isWindows = System.properties['os.name'].contains("Windows");
String ext = isWindows?".exe":""
File exeFile = new File( basedir, "target/jet/build/AppWithDep" + ext);
assert exeFile.exists()
exeFile = new File( basedir, "target/jet/app/AppWithDep" + ext)
assert exeFile.exists()

File dep = new File(basedir, "target/jet/build/lib/commons-io-1.3.2.jar")
File prj = new File(basedir, "target/jet/build/AppWithDep.prj")
File junitLib = new File(basedir, "target/jet/build/lib/junit-4.8.2.jar")
File jacksonDep = new File(basedir, "target/jet/build/lib/jackson-databind-2.8.0.jar")
File log4jDep = new File(basedir, "target/jet/build/libs/log4j-1.2.12.jar")
File extDirContent= new File(basedir, "target/jet/build/extDir/test.txt")

dep.exists()
prj.text.contains("""!classpathentry lib/commons-io-1.3.2.jar
  -optimize=autodetect
  -protect=nomatter
  -pack=all
!end""")

junitLib.exists()
prj.text.contains("""!classpathentry lib/junit-4.8.2.jar
  -optimize=all
  -protect=all
  -pack=all
!end""")

jacksonDep.exists()
prj.text.contains("""!classpathentry lib/jackson-databind-2.8.0.jar
  -optimize=autodetect
  -protect=nomatter
  -pack=none
!end""")

log4jDep.exists()
prj.text.contains("""!classpathentry libs/log4j-1.2.12.jar
  -optimize=autodetect
  -protect=nomatter
  -pack=none
!end""")

extDirContent.exists()

String cmd = exeFile.getAbsolutePath();

assert (cmd.execute().text.trim().equals("HelloWorld"))
