import { Fragment, useState } from "react";
import {
  GridContainer,
  GridOptionItem,
  GridOptionImage,
  Text,
  Detail,
  GridOptionTextContainer,
} from "./style";
import { DescriptionOptionModalProps } from "../../../types/options.types";
import OptionDescriptionModal from "../OptionDescriptionModal/OptionDescriptionModal";

type ListType = {
  id: number;
  name: string;
  imgUrl: string;
  description: string;
};

type GridOptionListProps = {
  list: ListType[];
};

export default function GridOptionList({ list }: GridOptionListProps) {
  const [isDescriptionModalOpen, setIsDescriptionModalOpen] =
    useState<boolean>(false);
  const [detailOption, setDetailOption] =
    useState<DescriptionOptionModalProps>();

  return (
    <Fragment>
      <GridContainer>
        {list.map((item) => (
          <GridOptionItem key={item.id}>
            <GridOptionImage src={item.imgUrl} />
            <GridOptionTextContainer>
              <Text $size={0.875}>{item.name}</Text>
              <Detail
                onClick={() => {
                  setDetailOption(item);
                  setIsDescriptionModalOpen(true);
                }}
              >
                자세히 보기 &gt;
              </Detail>
            </GridOptionTextContainer>
          </GridOptionItem>
        ))}
      </GridContainer>
      {isDescriptionModalOpen && (
        <OptionDescriptionModal
          setIsDescriptionModalOpen={setIsDescriptionModalOpen}
          option={detailOption as DescriptionOptionModalProps}
          isTrimSelect={false}
        />
      )}
    </Fragment>
  );
}
