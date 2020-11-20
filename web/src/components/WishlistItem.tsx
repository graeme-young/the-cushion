import { Box, Flex, Heading, Image, Link, Text } from "@chakra-ui/core";
import React from "react";

interface WishlistItemProps {
  name: String;
  imageUri: string;
  price: Number;
  onClick: () => {};
}

export const WishlistItem: React.FC<WishlistItemProps> = ({
  name,
  imageUri,
  price,
}) => {
  return (
    <Flex p={5} h={150}>
      <Image src={imageUri} h="100%" />
      <Box bg="lightgrey" w="100%">
        <Flex justifyContent="space-between">
          <Link>
            <Heading m={2} size="sm">
              {name}
            </Heading>
          </Link>
          <Text m={2}>${price}</Text>
        </Flex>
      </Box>
    </Flex>
  );
};
