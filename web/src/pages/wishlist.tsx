import {
  Flex,
  Heading,
  Stack,
  Image,
  Box,
  Link,
  Text,
  IconButton,
  Icon,
} from "@chakra-ui/core";
import React, { useState } from "react";
import { Navbar } from "../components/Navbar";
import { WishlistItem } from "../components/WishlistItem";
import { AiOutlineEye, AiOutlineDelete, AiOutlineHeart } from "react-icons/ai";

interface wishlistProps {}

const wishlist: React.FC<wishlistProps> = ({}) => {
  const wishlist = [
    {
      name: "Couch",
      imageUri:
        "https://secure.img1-ag.wfcdn.com/im/91378491/resize-h600-w600%5Ecompr-r85/8140/81409053/Sofas.jpg",
      price: 5000,
    },
    {
      name: "Chair",
      imageUri:
        "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.JPG",
      price: 400,
    },
  ];
  const [selectedItem, setSelectedItem] = useState(wishlist[0]);

  const selectItem = (item) => {
    setSelectedItem(item);
  };
  return (
    <>
      <Navbar />
      <Box m={4}>
        <Heading borderBottom="solid" borderBottomWidth={3}>
          My Wishlist
        </Heading>
        {wishlist.length == 0 ? (
          <Flex mt={2}>
            <Text>
              Looks like your wishlist is empty! You can add an item by clicking
              the
              <span>
                <IconButton
                  icon={AiOutlineHeart}
                  fontSize={25}
                  aria-label="Wishlist"
                  variant="ghost"
                  color="black"
                />
              </span>
              button on an item.
            </Text>
          </Flex>
        ) : (
          <Flex ml={4} justifyContent="space-between">
            <Stack spacing={2} flexGrow={1}>
              {wishlist.map((item) => (
                <Flex p={5} h={150} key={item.name}>
                  <Image src={item.imageUri} h="100%" />
                  <Box bg="lightgrey" w="100%">
                    <Flex justifyContent="space-between">
                      <Link>
                        <Heading m={2} size="sm">
                          {item.name}
                        </Heading>
                      </Link>
                      <Text m={2}>${item.price}</Text>
                    </Flex>
                  </Box>
                  <IconButton
                    icon={AiOutlineEye}
                    fontSize={25}
                    aria-label="Details"
                    h="110px"
                    w="20px"
                    variant="ghost"
                    borderRadius={0}
                    bg="lightgrey"
                    onClick={() => selectItem(item)}
                  />
                  <IconButton
                    icon={AiOutlineDelete}
                    fontSize={25}
                    aria-label="Details"
                    h="110px"
                    w="20px"
                    variant="ghost"
                    borderRadius={0}
                    bg="lightgrey"
                    onClick={() => selectItem(item)}
                  />
                </Flex>
              ))}
            </Stack>
            <Box mr={2}>
              <Image src={selectedItem?.imageUri} maxH="300px" />
              <Heading size="md">{selectedItem?.name}</Heading>
              <Text>${selectedItem?.price}</Text>
            </Box>
          </Flex>
        )}
      </Box>
    </>
  );
};

export default wishlist;
