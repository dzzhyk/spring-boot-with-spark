package com.yankaizhang.bootspark;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 基于用户的协同过滤推荐算法
 *
 * <p>
 * 首先输入n代表用户数，用户id分别为1~n
 * 随后n行，第i行代表id为i-1的用户(id从0开始)喜好的物品编号列表
 * <p>
 * <p>
 * 测试用例：
 * 4
 * 1 2 3
 * 1 3
 * 2 5
 * 3 4 5
 *
 * @author dzzhyk
 */
public class UserCollaborateFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // 用户相似度矩阵
        int[][] sparseMatrix = new int[n][n];

        // 用户->物品
        HashMap<Integer, Set<Integer>> userItems = new HashMap<>();

        // 物品->用户的倒排表 a物品: A B用户, b物品: A C用户
        HashMap<Integer, Set<Integer>> itemUsers = new HashMap<>();

        // 物品种类集合
        HashSet<Integer> itemSet = new HashSet<>();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String[] items = scanner.nextLine().split(" ");
            List<Integer> integers = Arrays.stream(items).map(Integer::parseInt).collect(Collectors.toList());

            userItems.put(i, new HashSet<>(integers));
            itemSet.addAll(integers);
            for (Integer item : integers) {
                itemUsers.putIfAbsent(item, new HashSet<>());
                itemUsers.get(item).add(i);
            }
        }

        // 更新用户相似度矩阵
        Set<Map.Entry<Integer, Set<Integer>>> entrySet = itemUsers.entrySet();
        for (Map.Entry<Integer, Set<Integer>> integerSetEntry : entrySet) {
            Set<Integer> users = integerSetEntry.getValue();
            for (Integer i : users) {
                for (Integer j : users) {
                    if (i.equals(j)) {
                        continue;
                    }
                    sparseMatrix[i][j] += 1;
                }
            }
        }

        // 计算用户之间的相似度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sparseMatrix.length; j++) {
                if (j != i) {
                    double sim = sparseMatrix[i][j] / Math.sqrt(userItems.get(i).size() * userItems.get(j).size());
                    System.out.println(i + "--" + j + " 相似度: " + sim);
                }
            }
        }

        // 计算所有用户对所有物品的推荐程度
        for (int i = 0; i < n; i++) {
            System.out.println("=============对"+i+"号用户进行推荐============");
            for (Integer item : itemSet) {
                Set<Integer> users = itemUsers.get(item);
                if (!users.contains(i)) {
                    // 如果当前用户i没有购买该物品，进行用户->物品推荐度计算
                    double userItemRecommendDegree = 0.0;
                    for (Integer user : users) {
                        userItemRecommendDegree += sparseMatrix[i][user] / Math.sqrt(userItems.get(i).size() * userItems.get(user).size());
                    }
                    System.out.println("推荐物品: " + item + ", 推荐度: " + userItemRecommendDegree);
                }else{
                    System.out.println("用户已经喜欢: " + item);
                }
            }
        }

        scanner.close();
    }
}
