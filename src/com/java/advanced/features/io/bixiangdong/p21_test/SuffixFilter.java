package com.java.advanced.features.io.bixiangdong.p21_test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author wangzhichao
 * @since 2021/8/4
 */
public class SuffixFilter implements FilenameFilter {
    private String suffix;

    public SuffixFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
}
