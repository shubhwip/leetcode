package others;

import java.util.ArrayList;
import java.util.List;

public class BrandTreeOperations {

    class BrandTree {
        Integer brandId;
        Integer parentBrandId;
        Integer hotelCount;
        List<BrandTree> childrenBrands;

        public BrandTree(Integer brandId, Integer parentBrandId, Integer hotelCount, ArrayList<BrandTree> childrenBrands) {
            this.brandId = brandId;
            this.parentBrandId = parentBrandId;
            this.hotelCount = hotelCount;
            this.childrenBrands = childrenBrands;
        }
    }

    class BrandDto {
        Integer brandId;
        Integer parentBrandId;
        Integer hotelCount;
    }

    List<BrandTree> buildBrandTree(List<BrandDto> brandDtos) {
        List<BrandTree> brandTrees = new ArrayList<>();
        for (BrandDto brandDto : brandDtos) {
            // ParentBrand is null
            if (brandDto.parentBrandId == null) {
                brandTrees.add(new BrandTree(brandDto.brandId, brandDto.parentBrandId, brandDto.hotelCount, new ArrayList<>()));
            }
            // ParentBrand is not null
            else {
                BrandTree bt = null;
                // Parent Brand already in brand tree
                for (BrandTree brandTree : brandTrees) {
                    bt = search(brandDto.parentBrandId, brandTree);
                }
                if (bt == null) {
                    bt = new BrandTree(brandDto.parentBrandId, null, 0, new ArrayList<>());
                    bt.childrenBrands.add(new BrandTree(brandDto.brandId, brandDto.parentBrandId, brandDto.hotelCount, new ArrayList<>()));
                }
                // Parent Brand not in brand tree
            }

        }
        return brandTrees;

    }

    private BrandTree search(Integer parentBrandId, BrandTree brandTree) {
        if (brandTree.parentBrandId == parentBrandId)
            return brandTree;
        for (BrandTree bt : brandTree.childrenBrands) {
            return search(parentBrandId, bt);
        }
        return null;
    }

}
