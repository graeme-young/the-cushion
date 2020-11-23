import { Box, Image, Text } from "@chakra-ui/core";
import React from "react";
import { Item } from "../../types";

interface ItemPreviewCardProps {
  item: Item;
}

export const ItemPreviewCard: React.FC<ItemPreviewCardProps> = ({ item }) => {
  return (
    <Box
      maxW="sm"
      borderWidth="1px"
      borderRadius="lg"
      overflow="hidden"
      alignItems="center"
    >
      <Image margin="auto" mt={15} src={item.imageUri} />

      <Box
        mt="1"
        fontWeight="semibold"
        as="h4"
        lineHeight="tight"
        isTruncated
        m="1"
      >
        {name}
      </Box>
      <Box m="1">
        <Text isTruncated>{item.description}</Text>
        <Text>${item.price}</Text>
      </Box>
    </Box>
  );
};
