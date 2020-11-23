import {
  Heading,
  Text,
  Link,
  IconButton,
  Button,
  Flex,
  Box,
} from "@chakra-ui/core";
import { HiOutlineShoppingCart } from "react-icons/hi";
import { AiOutlineShopping, AiOutlineHeart } from "react-icons/ai";
import { BsPerson } from "react-icons/bs";
import NextLink from "next/link";
import React from "react";
import axios from "axios";
import { useQuery } from "react-query";

interface NavbarProps {}

export const Navbar: React.FC<NavbarProps> = ({}) => {
  const { data, isLoading, error, isError } = useQuery("fetchMe", async () => {
    return await axios.get("http://localhost:8080/api/users/me");
  });

  return (
    <Flex position="sticky" zIndex={1} top={0} bg="black">
      <Flex flex={3} m="auto" align="center" minW={800}>
        <NextLink href="/">
          <Link fontWeight={20} ml={15} color="white" fontSize={80}>
            The Cushion.
          </Link>
        </NextLink>

        <Box ml="auto" w="25%">
          <Flex flexDir="row" justifyContent="flex-end">
            {!data?.data.success ? (
              <NextLink href="/login">
                <IconButton
                  icon={BsPerson}
                  fontSize={25}
                  aria-label="Wishlist"
                  h="100px"
                  w="33%"
                  variant="ghost"
                  color="white"
                  borderRadius={0}
                />
              </NextLink>
            ) : (
              <>
                <NextLink href="/wishlist">
                  <IconButton
                    icon={AiOutlineHeart}
                    fontSize={25}
                    aria-label="Wishlist"
                    h="100px"
                    w="33%"
                    variant="ghost"
                    color="white"
                    borderRadius={0}
                  />
                </NextLink>
                <NextLink href="/cart">
                  <IconButton
                    icon={HiOutlineShoppingCart}
                    fontSize={25}
                    aria-label="Cart"
                    h="100px"
                    w="33%"
                    variant="ghost"
                    color="white"
                    borderRadius={0}
                  />
                </NextLink>
              </>
            )}
            <NextLink href="/shop">
              <IconButton
                icon={AiOutlineShopping}
                fontSize={25}
                aria-label="Shop"
                h="100px"
                w="33%"
                variant="ghost"
                color="white"
                borderRadius={0}
              />
            </NextLink>
          </Flex>
        </Box>
      </Flex>
    </Flex>
  );
};
