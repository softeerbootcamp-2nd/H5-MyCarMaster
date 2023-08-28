import { Fragment, useState } from "react";
import {
  GridContainer,
  GridOptionItem,
  GridOptionImage,
  GridNameText,
  GridNameLongText,
  DetailText,
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
  page: number;
};

export default function GridOptionList({ list, page }: GridOptionListProps) {
  const [isDescriptionModalOpen, setIsDescriptionModalOpen] =
    useState<boolean>(false);
  const [detailOption, setDetailOption] =
    useState<DescriptionOptionModalProps>();

  const currentData = list.slice((page - 1) * 10, page * 10);

  return (
    <Fragment>
      <GridContainer>
        {currentData.map((item) => (
          <GridOptionItem key={item.id}>
            <GridOptionImage src={item.imgUrl} />
            <GridOptionTextContainer>
              {item.name.length > 20 ? (
                <GridNameLongText>{item.name}</GridNameLongText>
              ) : (
                <GridNameText>{item.name}</GridNameText>
              )}

              <DetailText
                onClick={() => {
                  setDetailOption(item);
                  setIsDescriptionModalOpen(true);
                }}
              >
                자세히 보기 &gt;
              </DetailText>
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
