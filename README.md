# LoadApp
仿照微信双开、QQ双开等实现app应用双开，即在免安装情况下加载使用原生app，采用仿支付宝加载应用和微信加载小程序的功能特色，实现应用免安装情况下运行。

本项目主要采用插件化开发技术，在一个容器app中，把其它的app以文件流的形式进行加载，利用反射等方法获得相关的程序入口，进行加载和使用。

加载app的方法都封装了需要参数，根据方法名结合自身需要传输的参数即可了解需要使用的方法，只需要一行代码进行调用和实现。

# 最新版本

版本号：[![](https://www.jitpack.io/v/YouAreOnlyOne/LoadApp.svg)](https://www.jitpack.io/#YouAreOnlyOne/LoadApp)

使用自行替换下面的版本号，以获得最新版本。

# 使用体验

1、首先下载容器app，也是主app，这个app可以独立运行，也就是普通的app应用程序，需要下载并安装，下载地址：

    https://github.com/YouAreOnlyOne/LoadApp/blob/master/source/loadapp.apk。
    
    安装之后，直接运行，但是只有简单的HelloWorld界面，点击“加载APP”也不会有什么反应，不能够跳转到其它的app，会提示“文件是否存在”。

2、下载另一个需要的app，这个app也可以独立安装，也是一个普通的app应用程序，可以安装也可以不安装（实现双开效果需要安装），但是要把安装包（apk文件）放到手机存储的根目录下面，下载地址：

    https://github.com/YouAreOnlyOne/LoadApp/blob/master/source/develop.apk 。
    
    此时，重新进入主app，点击“加载APP”，就会把这个这一步下载的app加载进去。实现免安装使用。我们我们加载的这个app也是一个独立app，可以进行安装，安装之后就会达到容器里面加载了这个app，本身这个app又可以安装使用，达到应用双开的效果。
    

# 使用方法

这里分别介绍在不同项目环境中，如何引用对该库的依赖。

## Android中使用：

方法一：

1.第一步，在项目的build.gradle下配置，注意是项目的build.gradle：

     allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
    
    
2.第二步,在app的build.gradle下添加如下依赖：

    dependencies {
            ...
            implementation 'com.github.YouAreOnlyOne:LoadApp:版本号'
            ...
     }
    
    
方法二：
    
 1.第一步，下载依赖的包：
 
    https://github.com/YouAreOnlyOne/LoadApp/blob/master/source/standard-release.aar 。
    https://github.com/YouAreOnlyOne/LoadApp/blob/master/source/develop-release.aar 。
    https://github.com/YouAreOnlyOne/LoadApp/blob/master/source/app-release.aar 。
    
    并放到项目的 libs 目录下面。
    
 2.第二步,在app的build.gradle下添加如下依赖，注意，两个依赖是平级关系：
    
    repositories {
       flatDir {
           dirs 'libs'
         }
    }
    
    dependencies {
            ...
            compile(name:'standard-release', ext:'aar')
            compile(name:'develop-release', ext:'aar')
            compile(name:'app-release', ext:'aar')
            ...
    }
 

	
# 使用示例：

## 创建容器APP

1、Android环境中随便新建一个普通Android项目（注意添加上面的依赖），在任何一个Activity中，调用下面一行代码即可：

    LoadApp.loadApp(this,new File("app文件的路径"));

	public class Caculator {
    		public int caculate(){
    	  		int i=0,j=100;
      			return j/i;
  		}
	}



## 创建其它APP

1、Android环境中随便新建一个普通Android项目（注意添加上面的依赖），新建一个Activity，这个Activity需要继承框架的BaseActivity，该Activity中的所有的上下文环境的this全部用that代替即可，其它没有任何要求，跟普通app开发完全一样。例如下面简单的demo：

	public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        that.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(that,"点击事件成功！",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(that,SecondActivity.class);
                startActivity(intent);
            }
        });
      }
    }
    
2、编译该应用程序，生成apk。把生成的apk放到手机存储卡里面，或者服务器上面。

3、上面容器APP中，可以读取手机存储卡里面的app文件，也可以从服务器先下载到本地，然后再读取。总之只要活得需要加载的APP的文件路径就可以了。
	
	
不会写文件下载的，可以参考一下下面的文章：

OkHttp 、Retrofit 、Volley 、RxJava、Novate在Android中Web网络请求一行代码解决。

https://blog.csdn.net/u014374009/article/details/82933127

一行代码实现Ftp文件上传、文件下载、文件删除和进度监听的工具类的使用。

https://blog.csdn.net/u014374009/article/details/82944107

 
 
# 项目用到的权限

  在manifest文件中添加访问的权限：
 

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

   

 
# 使用指南
 
 更新中……
 
# 相关介绍

一行代码解决AndFix热修复和热跟更新问题，集成了阿里的开源库，修复程序的缺陷bug漏洞和功能页面等.

https://blog.csdn.net/u014374009/article/details/83052178




# 其它信息

1.项目还有很多不完善的地方，欢迎大家指导。

2.项目持续更新开源，有兴趣加入项目或者跟随项目的伙伴，可以邮件联系！ 

3.关注或者喜欢或者尝试使用或者感兴趣的伙伴可以，点击 ~ follow、fork、star ~ 。

# 作者邮箱

ycj52011@outlook.com


