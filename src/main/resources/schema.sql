-- 员工管理系统数据库脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS employee_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE employee_db;

-- 员工表
CREATE TABLE IF NOT EXISTS employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    age INT COMMENT '年龄',
    gender VARCHAR(10) DEFAULT '男' COMMENT '性别',
    department VARCHAR(50) COMMENT '部门',
    position VARCHAR(50) COMMENT '职位',
    salary DECIMAL(10, 2) COMMENT '薪资',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    status TINYINT DEFAULT 1 COMMENT '状态：0-离职，1-在职',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_department (department),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工信息表';

-- 操作日志表
CREATE TABLE IF NOT EXISTS operation_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    operation VARCHAR(100) NOT NULL COMMENT '操作描述',
    operator VARCHAR(50) COMMENT '操作人',
    target_type VARCHAR(50) COMMENT '目标类型',
    target_id BIGINT COMMENT '目标ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- 插入测试数据
INSERT INTO employee (name, age, gender, department, position, salary, phone, email, status) VALUES
('张三', 28, '男', '技术部', 'Java开发工程师', 15000.00, '13800138001', 'zhangsan@example.com', 1),
('李四', 32, '女', '产品部', '产品经理', 18000.00, '13800138002', 'lisi@example.com', 1),
('王五', 26, '男', '技术部', '前端开发工程师', 14000.00, '13800138003', 'wangwu@example.com', 1),
('赵六', 30, '女', '人事部', 'HR专员', 12000.00, '13800138004', 'zhaoliu@example.com', 1),
('孙七', 35, '男', '技术部', '技术总监', 30000.00, '13800138005', 'sunqi@example.com', 1),
('周八', 27, '女', '市场部', '市场专员', 11000.00, '13800138006', 'zhouba@example.com', 1),
('吴九', 29, '男', '技术部', '后端开发工程师', 16000.00, '13800138007', 'wujiu@example.com', 1),
('郑十', 31, '女', '财务部', '财务主管', 20000.00, '13800138008', 'zhengshi@example.com', 1);
