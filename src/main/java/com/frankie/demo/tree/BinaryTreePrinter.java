package com.frankie.demo.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/1/6 9:26
 */
public class BinaryTreePrinter {

    public static void printNode(TreeNode root) {
        int maxLevel = BinaryTreePrinter.maxLevel(root);
        /**
         * singletonList(T o): return ann immutable list containing only the specified object.
         */
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> treeNodes, int level, int maxLevel) {
        if (treeNodes.isEmpty() || BinaryTreePrinter.isAllElementsNull(treeNodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BinaryTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode> newTreeNodes = new ArrayList<>();
        for (TreeNode treeNode : treeNodes) {
            if (treeNode != null) {
                System.out.print(treeNode.getVal());
                newTreeNodes.add(treeNode.getLeftNode());
                newTreeNodes.add(treeNode.getRightNode());
            } else {
                newTreeNodes.add(null);
                newTreeNodes.add(null);
                System.out.print(" ");
            }

            BinaryTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < treeNodes.size(); j++) {
                BinaryTreePrinter.printWhitespaces(firstSpaces - i);
                if (treeNodes.get(j) == null) {
                    BinaryTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (treeNodes.get(j).getLeftNode() != null)
                    System.out.print("/");
                else
                    BinaryTreePrinter.printWhitespaces(1);

                BinaryTreePrinter.printWhitespaces(i + i - 1);

                if (treeNodes.get(j).getRightNode() != null)
                    System.out.print("\\");
                else
                    BinaryTreePrinter.printWhitespaces(1);

                BinaryTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newTreeNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(TreeNode treeNode) {
        if (treeNode == null)
            return 0;

        return Math.max(BinaryTreePrinter.maxLevel(treeNode.getLeftNode()),
                BinaryTreePrinter.maxLevel(treeNode.getRightNode())) + 1;
    }

    private static boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
