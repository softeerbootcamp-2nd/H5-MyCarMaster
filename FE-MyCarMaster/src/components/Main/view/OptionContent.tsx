import { useEffect } from "react";
import { Flex, Image } from "@styles/core.style";
import { CategoryList, OptionDescription } from "@common/index";
import { useOptionDispatch, useOptionState } from "@contexts/OptionContext";
import { useTrimState } from "@contexts/TrimContext";
import { useDetailState } from "@contexts/DetailContext";
import { useCarPaintState } from "@contexts/CarPaintContext";
import filterOptionCategory from "@utils/Option/filterOptionCategory";
import { categories } from "@constants/Option.constants";
import { OptionType, OptionState } from "types/options.types";
import useFetch from "@hooks/useFetch";
import theme from "@styles/Theme";

interface FetchOptionsProps extends OptionType {
  result: {
    options: OptionType[];
  };
}

export default function OptionContent() {
  const { optionList, optionId }: OptionState = useOptionState();
  const optionDispatch = useOptionDispatch();

  const { trimId } = useTrimState();
  const { engineId, wheelDriveId, bodyTypeId } = useDetailState();
  const { interiorId } = useCarPaintState();

  const SERVER_URL = import.meta.env.VITE_APP_SERVER_URL;

  const { data } = useFetch<FetchOptionsProps>(
    `${SERVER_URL}/options?trimId=${trimId}&engineId=${engineId}&wheelDriveId=${wheelDriveId}&bodyTypeId=${bodyTypeId}&interiorColorId=${interiorId}`,
    {
      method: "GET",
    }
  );

  useEffect(() => {
    if (data) {
      optionDispatch({
        type: "SET_OPTION_LIST",
        payload: { optionList: data.result.options },
      });

      optionDispatch({
        type: "SET_OPTION_CATEGORY_INDEX",
        payload: {
          optionCategoryId: 0,
          optionId: data.result.options[0].id,
        },
      });
    }
  }, [data, optionDispatch]);

  const onClickHandler = (index: number) => {
    const nextOptionCategoryId = index;

    const filteredList = filterOptionCategory(
      categories,
      nextOptionCategoryId,
      optionList
    );

    optionDispatch({
      type: "SET_OPTION_CATEGORY_INDEX",
      payload: {
        optionCategoryId: nextOptionCategoryId,
        optionId: filteredList[0].id,
      },
    });
  };

  const ResizeImageHandler = (e: React.MouseEvent<HTMLImageElement>) => {
    const target = e.target as HTMLImageElement;

    if (target.style.animation) {
      target.style.animation = "1s hover_image_full_back ease-in-out forwards";

      setTimeout(() => {
        target.style.animation = "";
      }, 1000);
      return;
    }
    target.style.animation = "1s hover_image_full ease-in-out forwards";
  };

  const windowHeight = window.innerHeight;
  return (
    optionList?.length !== 0 && (
      <Flex
        $flexDirection="column"
        $justifyContent="flex-end"
        $alignItems="flex-start"
        $gap="5%"
        $overflow="hidden"
      >
        <Flex
          $maxHeight={windowHeight <= 950 ? "25rem" : ""}
          $justifyContent="space-between"
          $alignItems="flex-start"
          $gap="1rem"
        >
          <Image
            $width="50%"
            $height="85%"
            $borderRadius="0 100% 100% 0"
            $shadow="#222222 0 0 1rem"
            $onHover={true}
            onClick={(e) => ResizeImageHandler(e)}
            src={optionList.find((option) => option.id === optionId)?.imgUrl}
          />
          <OptionDescription
            option={
              optionList.find((option) => option.id === optionId) as OptionType
            }
          />
        </Flex>
        <CategoryList
          categories={categories}
          onClickHandler={(index) => onClickHandler(index as number)}
          $font={theme.fonts.Medium12_15}
          $switch={"option"}
        />
      </Flex>
    )
  );
}
