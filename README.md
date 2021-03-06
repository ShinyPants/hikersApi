# 踏青旅游论坛-服务端

# 接口

接口包含了普通用户的接口和管理员的接口。为了系统安全性和开发速度考虑，普通用户的接口管理员也可以用，但反过来不行，即用户接口集包含于管理员接口集，管理员的接口需要进行身份验证。

为了返回值的统一，设计了RespBody作为统一的返回值，即使出现运行错误，详细情况请点击[本链接](./readme/#项目实现.错误处理)。以下内容中，返回值均指的是RespBody中的data字段。

用户的接口通常以“/api”开头，而管理员的接口通常以“/adm”开头。

## 用户接口

### 获取分区

URL：/api/parts

方式：get

参数：无

返回值：

| 类型 | 字段   | 解释     |
| ---- | ------ | -------- |
| int  | id     | 分区编号 |
| str  | picUrl | 分区图标 |
| str  | name   | 分区名称 |
| int  | score  | 排序分数 |

## 管理员接口

### 添加分区

URL：/api/parts

方式：post

参数：

| 类型 | 字段   | 解释             |
| ---- | ------ | ---------------- |
| str  | picUrl | 分区图标         |
| str  | name   | 分区名称         |
| int  | score  | 排序分数（可选） |

返回值：一个链表，为更新后的分区，每个元素如下

| 类型 | 字段   | 解释     |
| ---- | ------ | -------- |
| int  | id     | 分区编号 |
| str  | picUrl | 分区图标 |
| str  | name   | 分区名称 |
| int  | score  | 排序分数 |

### 修改分区

URL：/api/parts

方式：put

参数：一个链表，为需要修改的元素，每个元素如下

| 类型 | 字段   | 解释                 |
| ---- | ------ | -------------------- |
| int  | id     | 分区编号（不能为空） |
| str  | picUrl | 分区图标（可选）     |
| str  | name   | 分区名称（可选）     |
| int  | score  | 排序分数（可选）     |

返回值：一个链表，为更新后的分区，每个元素如下

| 类型 | 字段   | 解释     |
| ---- | ------ | -------- |
| int  | id     | 分区编号 |
| str  | picUrl | 分区图标 |
| str  | name   | 分区名称 |
| int  | score  | 排序分数 |

![测试图片](./readme/测试图.jpg)