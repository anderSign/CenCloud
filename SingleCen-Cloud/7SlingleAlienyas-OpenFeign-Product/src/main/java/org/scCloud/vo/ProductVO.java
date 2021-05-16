package org.scCloud.vo;

import java.util.List;

/**
 * vo表示值对象，一般用于接受一些特定的参数
 */
public class ProductVO {
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public ProductVO(List<String> ids) {
        this.ids = ids;
    }

    public ProductVO() {
    }
}
