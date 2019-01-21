# SelfAdjointView
SelfAdjointView 是一个方便开发者实现滑动内联动效的安卓库，这些效果包括平行滑动、缩放和透明度变化，支持同时作用于一个布局，也支持用户自定义某些滑动内联动效。具体见下面动效图。欢迎**star 或者 pull request**

## 适用范围
- ScrollView/HorizontalScrollView
- ListView/GridView
- RecyclerView
- ViewPager
- 能滑动的其它ViewGroup

## 动效
---
<img src="gif/g1.gif" width="30%" > .............<img src="gif/g2.gif" width="30%"> 

---
<img src="gif/g3.gif" width="30%">..............<img src="gif/g4.gif" width="30%"> 

## 例子
[点我见安装包](./apk/sample-debug.apk)

## 如何使用?

### 配置
```
dependencies {
  // jCenter
  compile 'com.cysion:adjointlib:1.4.0'
}
```

### 平行滑动效果
目前这个效果仅支持作用于图片，可以与其它动效同时作用于图片。该图片应该是AdjointImageView或者其子类，同时该view应该被AdjointContainer包裹，此时，该container应该只有这一个子view..*注意*，若要有效果，纵向时需要图片本身的高/宽比大于AdjointContainer的，横向时，需要图片本身的宽/高比大于AdjointContainer的。

lib中提供了VerticalMoveStyle和HoriMoveStyle两种实现，可实现AdjointStyle接口自定义新的效果。。

参数| 类型|描述
-------|----------|-----
isVertical | boolean|AdjointImageView的配置参数，可在xml里定义，true代表纵向效果，false代表横向

#### Step 1

布局代码

```
...<sth like scrollview>...
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:background="#66ffffff"
              android:orientation="horizontal"
              android:padding="10dp">
    <com.cysion.adjointlib.view.AdjointContainer
        android:id="@+id/container"
        android:layout_width="180dp"
        android:layout_height="100dp"
        android:layout_gravity="center_horizontal"
        android:layout_margin="10dp"
        >
        <com.cysion.adjointlib.view.AdjointImageView
            android:id="@+id/img_holder_img"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:adjustViewBounds="true"
            android:background="#99ff0000"
            android:padding="3dp"
            />
    </com.cysion.adjointlib.view.AdjointContainer>
    ...other view...
</LinearLayout>
...</sth like scrollview>...
```

#### Step 2

获得滑动容器的位置信息，以Rect标示，并提供一个Locator来传递给AdjointContainer.

```java
public class SecondActivity extends AppCompatActivity implements Locator{
  	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		...find view..
	mContainer1 = (AdjointContainer) findViewById(R.id.adcontainer1);
	//绘制完成，获得滑动容器的位置、大小信息
	mScrollView.post(new Runnable() {
           @Override
       	   public void run() {
	   mScrollView.getGlobalVisibleRect(mR);
 	   mContainer1.setLocator(SecondActivity.this);
	   }
	});
..  }
    @Override
    public Rect getLocation() {
        return mR;
    }
	...
}
```

#### Step 3

创建AdjointStyle对象，并设置给容器。

```java
 AdjointStyle style= new VerticalMoveStyle();
 mContainer1.addStyle(style);
```
此时，滑动容器滑动时，图片也会滑动，产生逆差效果。


### Alpha/Scale

若想具有此类效果，View(s)应该置于AdjointContainer中..lib中提供了VerticalScaleStyle、VerticalAlphaStyle、HoriScaleStyle和HoriAlphaStyle等实现，可实现AdjointStyle接口来自定义新的效果。

另外，1.3.0版本提供了OnReachMiddleCallBack接口，用来获得中间的item，包括其位置。

AdStyle的配置:

参数| 类型|描述
-------|----------|-----
minAlpha| float|最低的透明度
minScale| float|最小的item大小
linearable| boolean|定义动画计算方式，true代表线性，false代表先大后小
linearPos| float|线性计算，达到最大值时对应的滑动容器位置比例
factor| float|缩放/透明度因子
privotX| float|缩放时的锚点X坐标	
#### Step 1

布局代码

```
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/scroller"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <com.cysion.adjointlib.view.AdjointContainer
            android:id="@+id/adcontainer1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="10dp">

            <com.cysion.adjointlib.view.AdjointImageView
                android:id="@+id/img_ad1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:adjustViewBounds="true"
                android:src="@mipmap/p1c"/>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="40dp"
                android:layout_alignParentBottom="true"
                android:layout_below="@+id/img_ad1"
                android:layout_centerHorizontal="true"
                android:text="Hello world"
                android:textColor="@color/colorPrimary"
                android:textSize="24sp"/>
        </com.cysion.adjointlib.view.AdjointContainer>
		....../other view.....
		....../other view.....
		....../other view.....
		....../other view.....
    </LinearLayout>

</ScrollView>

```

#### Step 2
见之前的step-2

#### Step 3

创建一个或多个AdjointStyle对象，并设置给容器。

```java
AdjointStyle style= new VerticalMoveStyle().minScale(0.9f);
AdjointStyle style2= new VerticalAlphaStyle();
..
 mContainer1.addStyle(style);
 mContainer1.addStyle(style2);
..
```
此时，滑动容器滑动时，目标View就会产生透明度变化/缩放/平行逆差等效果

##### 参见例子工程以查看更多细节. 

License
-------

    Copyright 2017 CysionLiu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
